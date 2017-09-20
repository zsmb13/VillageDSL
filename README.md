# Village DSL [![Build Status](https://travis-ci.org/zsmb13/VillageDSL.svg?branch=master)](https://travis-ci.org/zsmb13/VillageDSL)

This repository contains samples of various Kotlin DSL designs. It consists of two main exercises: a simple and an advanced model, and it offers various DSL solutions for constructing structures of these models. Both models aim to simulate a fantasy game of some sorts where you have to describe a village and its contents.

# The simple model

The [simple exercise](./src/main/kotlin/co/zsmb/villagedsl/simple) uses just three [model objects](./src/main/kotlin/co/zsmb/villagedsl/simple/models/Models.kt): the village, the houses it contains, and the people who are in those houses. Here are the classes representing these concepts:

```kotlin
data class Person(val name: String, val age: Int)
data class House(val people: List<Person>)
data class Village(val houses: List<House>)
```

Note that the examples included are larger than the code samples you see here below, look at the linked source files for the full examples.

## Approaches without DSLs

First, let's see how we can construct a hierarchy of these models without defining a DSL.

### [Traditional Java style construction](./src/main/kotlin/co/zsmb/villagedsl/simple/nodsl/javastyle/JavaStyle.kt)

This is the approach that we'd take if we had to use Java, just translated to Kotlin syntax. We create mutable lists for everything, add items to them one by one on separate lines, and then create the objects that contain these lists.

```kotlin
val houses = mutableListOf<House>()

val people1 = mutableListOf<Person>()
people1.add(Person("Emily", 31))
people1.add(Person("Hannah", 27))
people1.add(Person("Alex", 21))
people1.add(Person("Daniel", 17))

val house1 = House(people1)
houses.add(house1)

val village = Village(houses)
```

This has all the usual pain points that building a hierarchy in Java entails: we can't really see the hierarchy in the code, and we have to follow a weird, unnaturally twisted structure with our code because of the limitations of the API. More importantly, this style of code gets complicated to read and modify quite quickly. 

### [Slightly improved construction, with more idiomatic Kotlin](./src/main/kotlin/co/zsmb/villagedsl/simple/nodsl/kotlinstyle/KotlinStyle.kt)

We can improve on this by quite a bit by just nesting some of these calls and using the factory methods for collections provided by the Kotlin standard library.

```kotlin
val house1 = House(listOf(
        Person("Emily", 31),
        Person("Hannah", 27),
        Person("Alex", 21),
        Person("Daniel", 17)))
        
val village = Village(listOf(house1))
```

The code we have here is easier to write, read and maintain than the previous one. It still doesn't show hierarchy very well however, and it will face some of the same problems as the previous code when the problem gets larger. 

### [A "home-made" DSL](./src/main/kotlin/co/zsmb/villagedsl/simple/nodsl/homemade/Homemade.kt)

This "poor man's DSL" solution is mostly included for good measure. It makes use of the previously mentioned collection factory methods, named parameters, and some formatting to create something resembling a DSL. 

```kotlin
val village = Village(listOf(
            House(listOf(
                    Person(
                            name = "Emily",
                            age = 31
                    ),
                    Person(
                            name = "Hannah",
                            age = 27
                    ),
                    Person(
                            name = "Alex",
                            age = 21
                    ),
                    Person(
                            name = "Daniel",
                            age = 17
                    )
            ))
))
```

The problem here is that modifying the code is tedious compared to a real DSL, since you have to pay attention to where the `listOf` calls happen, and you can't just move around pieces of the code without having to check that all your commas are in the right place.

## DSL approaches

Below are various DSL approaches. Neither of these are supposed to be *the* solution to the posed exercise, they are just various examples of DSLs you can use to solve your problem. This document only contains small samples of how to use these DSLs, check the linked packages for the implementations and full examples. 

### [The usual DSL](./src/main/kotlin/co/zsmb/villagedsl/simple/dsl1)

To start, here's the DSL that follows the conventions most often used by DSL creators. 
This makes use of [function literals with receivers](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver), as well as [default](https://kotlinlang.org/docs/reference/functions.html#default-arguments) and [named](https://kotlinlang.org/docs/reference/functions.html#named-arguments) arguments.

```kotlin
val v = village {
    house {
        person {
            name = "Emily"
            age = 31
        }
        person(name = "Hannah") {
            age = 27
        }
        person("Alex", 21)
        person(age = 17, name = "Daniel")
    }
}
```

Note that while above a variety of different ways for calling the same `person` function is showcased, sticking to one of these styles at a time is obviously recommended.

You can see that even this simplest DSL gets rid of the modification woes of the non-DSL approaches, as the blocks here can be moved around freely and easily.

### [A more interesting DSL with operator overloading](./src/main/kotlin/co/zsmb/villagedsl/simple/dsl2)

Instead of doing the same thing over and over on every level of the hierarchy as before, we can construct some of our models (usually the leafs of the hierarchy) more easily by just calling their constructors, and adding them to the right parent using overloaded operators. (A good example of this is how text can be appended to elements in the [`kotlinx.html`](https://github.com/Kotlin/kotlinx.html) library.)

```kotlin
val v = village {
    house {
        +Person("Emily", 31)
        +Person("Hannah", 27)
        +Person("Alex", 21)
        +Person("Daniel", 17)
    }
}
```

This is done by using an overloaded `unaryPlus` operator, that's defined as a [member](./src/main/kotlin/co/zsmb/villagedsl/simple/dsl2/Implementation.kt#L15) of the class responsible for creating a `House`. This way, both the list containing people that will be in the `House` and the constructed `Person` object are in scope inside the function. 

The same can be done using the `unaryMinus` operator, as you can see in the full sample. This can provide a nice "list" feeling in your DSL.

### [A slightly over-the-top DSL](./src/main/kotlin/co/zsmb/villagedsl/simple/dsl3)

Pushing the limits of the Kotlin language, using some dummy `object`s and infix functions can you can get pretty far with making your DSL fluent, and read almost like sentences. (A good official example is the [`KotlinTest`](https://github.com/kotlintest/kotlintest) library.)

```kotlin
val v = village containing houses {

    house with people {
        "Emily" age 31
        "Hannah" age 27
        "Alex" age 21
        "Daniel" age 17
    }
        
}
```

# The advanced model

The [advanced example](./src/main/kotlin/co/zsmb/villagedsl/advanced)'s model extends the simple model with various types of loot that can be placed inside the houses.

```kotlin
data class Village(val houses: List<House>)
data class House(val people: List<Person>, val items: List<Item>)
data class Person(val name: String, val age: Int)

interface Item
data class Gold(val amount: Int) : Item

interface Weapon : Item
data class Sword(val strength: Double) : Weapon

interface Armor : Item
data class Shield(val defense: Double) : Armor  
```

Just to reiterate before getting into the various approaches: the examples here are just short snippets. The full example is included in the source files linked below.

## Approaches without DSLs

Again, let's take a look at how we can create instances of the above models and nest them appropriately without defining a DSL to do so first.

### [Traditional Java style construction](./src/main/kotlin/co/zsmb/villagedsl/advanced/nodsl/javastyle/JavaStyle.kt)

This approach doesn't really deserve any more explanation than it got at the simple example. It's tedious to write, hard to both read and modify.

```kotlin
val houses = mutableListOf<House>()

val people1 = mutableListOf<Person>()
people1.add(Person("Alice", 31))
people1.add(Person("Bob", 45))
val items1 = mutableListOf<Item>()
items1.add(Gold(500))
val house1 = House(people1, items1)
houses.add(house1)

val village = Village(houses)
```

### [A "home-made" DSL](./src/main/kotlin/co/zsmb/villagedsl/advanced/nodsl/homemade/Homemade.kt)

Nesting these calls gets you a bit closer to a DSL, but this solution has the same issues as it had with the simple model. `listOf` calls are ugly, commas are easy to miss and difficult to maintain.

```kotlin
val village = Village(listOf(
            House(listOf(
                    Person(
                            name = "Alice",
                            age = 31
                    ),
                    Person(
                            name = "Bob",
                            age = 45
                    )
            ), listOf(
                    Gold(
                            amount = 500
                    )
            ))
))
```

## DSL approaches

### [A traditional DSL](./src/main/kotlin/co/zsmb/villagedsl/advanced/dsl1)

Same old, same old. Lambdas with receivers and builder classes. This is the no-thrills DSL for this problem.

```kotlin
val v = village {
    house {
        person {
            name = "Alice"
            age = 31
        }
        person {
            name = "Bob"
            age = 45
        }
        gold {
            amount = 500
        }
    }
}
```

### [A weird, overkill DSL](./src/main/kotlin/co/zsmb/villagedsl/advanced/dsl2)

Here's something you probably shouldn't do. I included the entire village in this sample code so that you can see more of what this solution includes. While this is a really weird and unnecessary DSL, the extensible structure of its implementation is worth looking at.

```kotlin
val v = village {
    house {
        "Alice" age 31
        "Bob" age 45
        500.gold
    }
    house {
        sword with strength value 24.2
        sword with strength level 16.7
        shield with defense value 15.3
    }
    house()
    house {
        "Charles" age 52
        2500.gold
        sword
        shield
    }
}
```

# Tests

All approaches for both exercises contain [tests](./src/test/kotlin/co/zsmb/villagedsl), which check that the required structure was produced by all approaches. These tests make use of the default `toString` implementations of data classes, and simply assert that the output of the constructs in the `main` functions are the same as the expected `String`.

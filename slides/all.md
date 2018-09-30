## TypeSafety for Productivity

_How Types can increase developer productivity_

|>

### Context: A Booking webapp

Rewrite of a legacy application to handle dinner table bookings

Actual handling still in legacy, data entry in web-app

|>

Booking code is entered in a text input field

|>

Customer complaint: Booked a table, but no confirmation

|>

Legacy logs show error in booking code

    ERROR: booking code is too long (got 14, should be 8)
    ERROR: booking code is non numerical

<|

### Example 1

Let's find the bug

|>

Think about <i>when</i> we encounter the bug, and how it relates to our webapp lifecycle.

At what point in the flow does the error happen? <!-- .element: class="fragment" data-fragment-index="2" -->

That's right. It doesn't. It happens outside our application. <!-- .element: class="fragment" data-fragment-index="3" -->

|>

Let's fix the bug

    ERROR: booking code is too long (got 14, should be 8)
    ERROR: booking code is non numerical

<|

### Example 2

Advance point of failure to our web application

|>

Introduced domain validation on a `Booking`.

Let's see what code breaks in tests.  <!-- .element: class="fragment" data-fragment-index="2" -->

|>

At what point in the flow does the error happen this time?

Good enough? <!-- .element: class="fragment" data-fragment-index="2" -->

<|

### Example 3

Advance point of failure to as soon as it enters our application: in the REST api class.

Let's introduce a Wrapper object BookingCode and make it compile. <!-- .element: class="fragment" data-fragment-index="2" -->

|>

Let's see what tests break this time.

|>

At what point in the flow does the error happen this time?

How do we prevent these kinds of bugs in the future? <!-- .element: class="fragment" data-fragment-index="2" -->

<|

### Foray into developer laziness

There are two kinds of laziness: the right kind and the wrong kind

|>

#### Wrong kind of laziness

_Current you_ is lazy

|>

The wrong kind of laziness cuts corners.

Strings and other primitives are passed as arguments.

It's not intention revealing (1 of the 4 rules of simple design). <!-- .element: class="fragment" data-fragment-index="2" -->

|>

In the end this costs productivity: 

When TDD'ing a method that takes a primitive theoretically you should test all edge cases

|>

#### Right kind of laziness

_Future you_ is lazy

|>

The right kind of laziness is defensive

Think about edge cases once and make rules explicit in a (Wrapper) class <!-- .element: class="fragment" data-fragment-index="2" -->

Wrapper passed as argument eliminates testing for edge cases on that method <!-- .element: class="fragment" data-fragment-index="2" -->

|>

#### No need to take long
Writing a new Wrapper class also doesn't need to take long.

Let me show you.

<|

### Example 4
#### The curious case we missed

This is me gambling you didn't pay too much attention :)

|>

Who can tell me what we missed? More specifically in the Booking.bookingCodeAsString(). <!-- .element: class="fragment" data-fragment-index="2" -->

Eventhough BookingCode can be null, our Booking always expects there to be one. <!-- .element: class="fragment" data-fragment-index="3" -->

Thanks for NPE's Java! <!-- .element: class="fragment" data-fragment-index="4" -->

|>

### I guess we fix it with Optional?

Let's check out the code

<|

### Java's Optional

Optional **does** express that some property might not exist.

But it's not very easy to work with: long chains of .map add verbosity

And it's not serializable <!-- .element: class="fragment" data-fragment-index="2" -->

And you're not allowed to actually USE it as a field <!-- .element: class="fragment" data-fragment-index="3" -->

Or use it as a parameter for that matter <!-- .element: class="fragment" data-fragment-index="3" -->

|>

#### Optional is a glorified if-statement

<|

### There's gotta be a better way!

And there is! <!-- .element: class="fragment" data-fragment-index="2" -->

<|

# Kotlin! <3

|>

## Optional

Kotlin has the concept of optionality built-in

    val code: BookingCode? //optional
    val code: BookingCode  //non optional

|>

## Immutability

Kotlin also has immutability built-in:

    data class BookingCode(val code: String)

No longer do you have to write tedious equals() and hashcode() reflection-equals implementations!

|>

## Closed by default

You ever **really** tried to prevent access to methods or classes of an Aggregate in Java?

|>

Here's how you do it in Kotlin:

    class Booking()

|>

If you want a public class or method you'll have to add the `open` modifier to it:

    open class Booking() {
        open fun reschedule(val nextDate: LocalDateTime) { ... }
    }

|>

## Named Parameters

Kotlin also has named parameters, essentially removing the need for TestBuilders

    val booking = 
        Booking(
            bookingCode = BookingCode("87654321"), 
            name = "snarf"
        )

|>

## Extension functions

Kotlin also has extension functions, replacing "defaults" in TestBuilders

    fun BookingCode.Companion.defaultBookingCode() = 
        BookingCode("87654321")

|>

## Sealed classes (aka traits, aka union types)

_Sealed classes_ allow you to be even more expressive with your types.

|>

## Sealed classes

Given this Sealed Class:

    sealed class RoverCommand
    data class Forward(val steps: Int): RoverCommand()
    data class Backward(val steps: Int): RoverCommand()
    object Left: RoverCommand() //these don't take arguments
    object Right: RoverCommand() //these don't take arguments

|>

You can do pattern matching:

    fun handle(cmd: RoverCommand) = when(cmd) {
        is Forward -> moveForward(cmd.steps)
        is Backward -> moveBackward(cmd.steps)
        is Left -> turnLeft()
        is Right -> turnRight()
    }

|>

This won't compile:

    fun handle(cmd: RoverCommand) = when(cmd) {
        is Forward -> moveForward(cmd.steps)
        is Left -> turnLeft()
        is Right -> turnRight()
    }

The compiler will tell you you're missing the case of Backward

|>

This is about as "early" as one can get into knowing when a bug would happen.

<|

# Conclusion

Not using Types **costs** time:

* Unclear what the rules are of a primitive (not intent revealing) <!-- .element: class="fragment" data-fragment-index="2" -->
* Theoretically TDD all edge cases <!-- .element: class="fragment" data-fragment-index="2" -->

|>

* Write Wrapper classes (from the start!)
* Kotlin has a set of features that can help with that  <!-- .element: class="fragment" data-fragment-index="2" -->
* Use Sealed classes whenever you can, to learn asap about potential bugs <!-- .element: class="fragment" data-fragment-index="3" -->

<|

# Thanks!

<|

## Stuff to explore

Kotlin is still only _half strong typed_

Pure FP languages (like Elm) really are _strong typed_, which comes with even more productivity gains

|>

An important notion is I/O:

The boundary between _the real world_ and a _type safe system_

|>

Elm interacts with REST API's too 

AKA strings that can mean anything

The boundary lies in the Json.Decode Elm library

|>

Elm also interacts with JavaScript or your browser

AKA other untyped objects

Here the boundary lies in Elm's _Ports_

<|

## Go and explore!

* [Kotlin](https://kotlinlang.org/docs/reference/)
* [Elm](https://guide.elm-lang.org/)
* [Elm Ports](https://hackernoon.com/how-elm-ports-work-with-a-picture-just-one-25144ba43cdd)

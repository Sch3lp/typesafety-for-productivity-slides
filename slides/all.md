## TypeSafety for Productivity

_How Types can increase developer productivity_

|>

### Context: A Booking webapp

We're rewriting a legacy application that would store bookings (hotel room, kayaks, dinner tables, ...)

Bookings are still handled in the old legacy app by the backoffice, so we'll need to forward our Bookings made by the webapp.

|>

`BookingCode` is just a `String`

...or is it? <!-- .element: class="fragment" data-fragment-index="2" -->

<|

### Example 1

Where we still were naive and thought `BookingCode` was just a `String`.

|>

Think about <i>when</i> we encounter the bug, and how it relates to our webapp lifecycle.

At what point in the flow does the error happen? <!-- .element: class="fragment" data-fragment-index="2" -->

That's right. It doesn't. It happens outside our application. <!-- .element: class="fragment" data-fragment-index="3" -->

<|

### Example 2

Let's advance it to at least occur inside our application.

|>

Here we just introduced domain validation on a `Booking`.

Let's see what code breaks.  <!-- .element: class="fragment" data-fragment-index="2" -->

|>

Again, at what point in the flow does the error happen?

Think about when you'd be debugging, what's your next intuition? <!-- .element: class="fragment" data-fragment-index="2" -->

If you thought to move up the BREAKING POINT in the flow, you're on to something. <!-- .element: class="fragment" data-fragment-index="3" -->

<|

### Example 3

Let's advance it to occur as soon as it enters our application: in the Resource.

We'll use a Wrapper object called BookingCode that encapsulates its rules.

|>

Let's see what tests break this time.

|>

Again, at what point in the flow does the error happen?

How do we prevent these kinds of bugs in the future? <!-- .element: class="fragment" data-fragment-index="2" -->

Do you think you can learn about possible bugs like this faster? <!-- .element: class="fragment" data-fragment-index="3" -->

<|

### The curious case we missed (Example 4)

This is me gambling you didn't pay too much attention :)

Who can tell me what we missed? More specifically in the Booking. <!-- .element: class="fragment" data-fragment-index="2" -->

Eventhough BookingCode can be null, our Booking always expects there to be one. <!-- .element: class="fragment" data-fragment-index="3" -->

Thanks for NPE's Java! <!-- .element: class="fragment" data-fragment-index="4" -->

|>

### I guess we fix it with Optional?

Optional **does** express that some property might not exist.

But it's not very easy to work with.

And it's not serializable.

And you're not allowed to actually **use** it as a field.

Or use it as a parameter for that matter.

This really just makes Optional a glorified if-statement.

<|

### There's gotta be a better way!

And there is! <!-- .element: class="fragment" data-fragment-index="2" -->

<|

# Kotlin! <3
package be.swsb.typesafety.example5.domain.booking

// This is now a useless class, because of named parameters. e.g.:
// Booking(bookingCode = "someBookingCode", id = someUUID, name = "whatever")
// all other params use defaults of the Booking constructor

//class BookingTestBuilder private constructor() {
//    private var bookingCode: BookingCode? = null
//
//    fun build(): Booking {
//        return Booking.booking(bookingCode)
//    }
//
//    fun withBookingCode(bookingCode: Optional<BookingCode>): BookingTestBuilder {
//        this.bookingCode = bookingCode
//        return this
//    }
//
//    companion object {
//
//        fun booking(): BookingTestBuilder {
//            return BookingTestBuilder()
//        }
//
//        fun defaultBooking(): BookingTestBuilder {
//            return booking().withBookingCode(randomBookingCode())
//        }
//    }
//}

// Since code is currently the only thing we can change,
// let's just write an extension function which will only be available in the tests module
// Compare this to the code above, which does the same
fun Booking.Companion.defaultBooking() = Booking.booking(BookingCode.randomBookingCode())
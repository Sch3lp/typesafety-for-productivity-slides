package be.swsb.typesafety.example5.domain.booking

import be.swsb.typesafety.example5.domain.DomainValidationRuntimeException
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

internal class BookingCodeTest {

    @Test
    fun bookingCodeMustBe8Numerical() {
        BookingCode.bookingCode("12345678")
        assertThatThrownBy { BookingCode.bookingCode("123456789") }
                .isInstanceOf(DomainValidationRuntimeException::class.java)
                .hasMessage("A BookingCode should be at most 8 numerical characters")
        assertThatThrownBy { BookingCode.bookingCode("12ab5678") }
                .isInstanceOf(DomainValidationRuntimeException::class.java)
                .hasMessage("A BookingCode should be at most 8 numerical characters")
    }

    @Test
    fun bookingCodeWithEmpty_ReturnsNull() {
        assertThat(BookingCode.bookingCode("")).isNull()
    }

// this is now a useless test because bookingCode() returns a "?"
//    @Test
//    fun bookingCodeWithNull_ReturnsNull() {
//        assertThat(BookingCode.bookingCode(null!!)).isNull()
//    }


// this is now a useless test because BookingCode is a "data class"
//    @Test
//    fun bookingCodesAreEqualWhenCodesAreEqual() {
//        val bookingCode1 = BookingCode.bookingCode("09876543")
//        val bookingCode2 = BookingCode.bookingCode("09876543")
//        val bookingCode3 = BookingCode.bookingCode("12345678")
//
//        assertThat(bookingCode1 == bookingCode2).isTrue()
//        assertThat(bookingCode2 == bookingCode1).isTrue()
//        assertThat(bookingCode2 == bookingCode3).isFalse()
//        assertThat(bookingCode3 == bookingCode1).isFalse()
//        assertThat(bookingCode1 == null).isFalse()
//    }
}
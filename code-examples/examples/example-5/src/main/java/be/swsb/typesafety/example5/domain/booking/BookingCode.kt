package be.swsb.typesafety.example5.domain.booking

import be.swsb.typesafety.example5.domain.DomainValidationRuntimeException
import org.apache.commons.lang3.StringUtils.isEmpty

import java.util.regex.Pattern

data class BookingCode constructor(private val bookingCode: String) {

    init {
        if (!Pattern.matches("[0-9]{1,8}", bookingCode)) {
            throw DomainValidationRuntimeException("A BookingCode should be at most 8 numerical characters")
        }
    }

    fun asString(): String {
        return bookingCode
    }

    companion object {
        fun bookingCode(bookingCode: String): BookingCode? {
            return if (isEmpty(bookingCode)) {
                null
            } else {
                BookingCode(bookingCode)
            }
        }
    }
}

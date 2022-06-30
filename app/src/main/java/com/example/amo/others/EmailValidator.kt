package com.example.amo.others

class EmailValidator {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        fun is_valid_email(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email)
        }
    }
}
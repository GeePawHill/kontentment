package org.geepawhill.contentment.format

class MissingFormatException(style: String, format: String) : RuntimeException("Missing format '$style' in format: '$format'.")

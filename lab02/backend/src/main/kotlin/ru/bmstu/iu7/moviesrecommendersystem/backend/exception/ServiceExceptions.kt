package ru.bmstu.iu7.moviesrecommendersystem.backend.exception

open class ServiceException(message: String?) : RuntimeException(message)

class InternalServiceException : ServiceException("Internal Service Error")

class NotFoundException(message: String?) : ServiceException(message)

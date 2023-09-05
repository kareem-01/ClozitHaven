package com.example.entity.utils

open class RemoteException(message: String?) : Exception(message)
open class NetWorkException(message: String?) : RemoteException(message)
class NoInternetException(message: String?) : NetWorkException(message)
class NullDataException(message: String?): RemoteException(message)
class ServerException(message: String?): RemoteException(message)
class BadEmailException(message: String?):RemoteException(message)
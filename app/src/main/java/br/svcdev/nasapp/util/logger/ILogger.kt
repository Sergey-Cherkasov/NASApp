package br.svcdev.nasapp.util.logger

interface ILogger {
    fun log_d(tag: String?, message: String)
    fun log_d(tag: String?, message: String, throwable: Throwable)
    fun log_e(tag: String?, message: String)
    fun log_e(tag: String?, message: String, throwable: Throwable)
    fun log_i(tag: String?, message: String)
    fun log_i(tag: String?, message: String, throwable: Throwable)
    fun log_v(tag: String?, message: String)
    fun log_v(tag: String?, message: String, throwable: Throwable)
    fun log_w(tag: String?, message: String)
    fun log_w(tag: String?, message: String, throwable: Throwable)
}
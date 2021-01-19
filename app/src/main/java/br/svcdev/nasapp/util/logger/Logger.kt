package br.svcdev.nasapp.util.logger

import android.util.Log

class Logger : ILogger {
    override fun log_d(tag: String?, message: String) {
        Log.d(tag, message)
    }

    override fun log_d(tag: String?, message: String, throwable: Throwable) {
        Log.d(tag, message, throwable)
    }

    override fun log_e(tag: String?, message: String) {
        Log.e(tag, message)
    }

    override fun log_e(tag: String?, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }

    override fun log_i(tag: String?, message: String) {
        Log.i(tag, message)
    }

    override fun log_i(tag: String?, message: String, throwable: Throwable) {
        Log.i(tag, message, throwable)
    }

    override fun log_v(tag: String?, message: String) {
        Log.v(tag, message)
    }

    override fun log_v(tag: String?, message: String, throwable: Throwable) {
        Log.v(tag, message, throwable)
    }

    override fun log_w(tag: String?, message: String) {
        Log.w(tag, message)
    }

    override fun log_w(tag: String?, message: String, throwable: Throwable) {
        Log.w(tag, message, throwable)
    }
}
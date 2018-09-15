package ir.laitec.model

enum class ErrorCode(val code : Int) {

    ERROR_INTERNET_CONNECTION(0){
        override fun signal() = ERROR_INTERNET_CONNECTION
    },
    ERROR_SERVER_CONNECTION(1){
        override fun signal() = ERROR_SERVER_CONNECTION
    };
    abstract fun signal(): ErrorCode
}
package ir.laitec.utils

abstract class Responsehandler {

    internal abstract fun onSuccess(result : String)

    internal abstract fun onfailure(error : Int)

}
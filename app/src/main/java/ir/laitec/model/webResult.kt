package ir.laitec.model

import org.json.JSONObject

data class webResult(val json : JSONObject) {

    var status : Int     = 0
    var code   : Int     = 0
    var msg    : String  = ""
    var data             = ArrayList<categoryModel>()


    override fun toString(): String {
        return "status : $status , code : $code , message = $msg , data = ${data.count()}"
    }
}
package network

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Avnish on 2/4/18.
 */
interface AppService {


    @POST(NetworkConstants.LOGIN)
    fun callLogin(@Body json: JSONObject): Call<ResponseBody>



}


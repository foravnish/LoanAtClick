package network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by Avnish on 2/8/18.
 */
class AppRetrofit {


    companion object {

        val instance by lazy {
            AppRetrofit.getAppRetrofit()
        }


        fun getAppRetrofit(): AppService {
            // To show the Api Request & Params
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(2, TimeUnit.MINUTES)
            httpClient.readTimeout(2, TimeUnit.MINUTES)
            httpClient.writeTimeout(5, TimeUnit.MINUTES)
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor(AuthInterceptor())

            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit.create(AppService::class.java)

        }

        class AuthInterceptor : Interceptor {


            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {

                var request = chain.request()

                // if (prefs != null && prefs.hasToken()) {//essentially checking if the prefs has a non null token
                request = request.newBuilder()
//                    .addHeader("Authorization", ""+ Prefences.getToken(ApplicationClass.getInstance()))
//                    .addHeader("Accept", "application/json")
//                    .header("Content-Type", "application/json")
//                    .header("Authorization", "Bearer "+ Prefences.getToken(ApplicationClass.getInstance()))
//                    .header("X-Requested-With", "XMLHttpRequest")
//                    .addHeader("Content-Type", "text/html")
                    .build()
                // }

                val mainResponse = chain.proceed(request)
//                if (mainResponse.code == 401) {
//                    ApplicationClass.getInstance().logoutUser("Your session has expired. Please login again to continue.");
//                }else if (mainResponse.code==500){
//                    Toast.makeText(ApplicationClass.getInstance(), "error 500" , Toast.LENGTH_SHORT).show()
//                }
//                else if (mainResponse.code==422){
//                    Toast.makeText(ApplicationClass.getInstance(), "error 422" , Toast.LENGTH_SHORT).show()
//                }


                return mainResponse
            }
        }
    }
}
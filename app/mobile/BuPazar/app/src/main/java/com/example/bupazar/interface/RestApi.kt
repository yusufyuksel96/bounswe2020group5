package com.example.bupazar.`interface`

import com.example.bupazar.model.LoginRequest
import com.example.bupazar.model.ProductDetails
import com.example.bupazar.model.RegisterRequest

import com.example.bupazar.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


/*
* Every method of an interface represents one possible API call. 
* It must have a HTTP annotation (GET, POST, etc.) to specify the request type and the relative URL.
* The return value wraps the response in a Call object with the type of the expected result.
*/
interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("/api/auth/login/")
    fun userLogin(@Body userData: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/auth/logout/")
    fun userLogout(@Header("Authorization") authToken: String): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/auth/google_login/")
    fun googleLogin(@Body authTokenRequest: AuthTokenRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/auth/register/")
    fun userRegister(@Body userData: RegisterRequest): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/auth/register_activate/")
    fun userVerificate(@Body userData: VerificationRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/products/{id}")
    fun productDetails(@Path("id") id: Long): Call<ProductDetails>

    @Headers("Content-Type: application/json")
    @GET("/api/products/")
    fun allProducts(): Call<Array<ProductDetails>?>

    @Headers("Content-Type: application/json")
    @POST("/api/products/subcategory/")
    fun subCategoryProducts(@Body subCategoryRequest: SubCategoryRequest): Call<Array<ProductDetails>?>

    @Headers("Content-Type: application/json")
    @POST("/api/products/category/")
    fun CategoryProducts(@Body categoryRequest: CategoryRequest): Call<Array<ProductDetails>?>

    @Headers("Content-Type: application/json")
    @GET(" /api/products/recommend/")
    fun recommendedProducts(@Header("Authorization") authToken: String): Call<Array<ProductDetails>?>

    @Headers("Content-Type: application/json")
    @POST(" /api/products/homepage/")
    fun featuredProducts(@Body featuredProductsRequest: FeaturedProductsRequest): Call<FeaturedProductsValue>

    @Headers("Content-Type: application/json")
    @POST("/api/chats/send_message/")
    fun sendMessage(@Header("Authorization") authToken: String, @Body chatRequest: ChatRequest): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/chats/get_last_message/")
    fun getLastMessage(@Header("Authorization") authToken: String, @Body chatRequest: ChatRequest): Call<GetLastMessageResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/chats/get_all_chats/")
    fun getAllChats(@Header("Authorization") authToken: String): Call<GetAllChatResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/chats/create_chat/")
    fun createChat(@Header("Authorization") authToken: String, @Body chatCreateRequest: ChatCreateRequest): Call<ChatCreateResponse>
    
    @Headers("Content-Type: application/json")
    @POST("/api/cart/edit/")
    fun addToCart(@Header("Authorization") authToken: String, @Body productData: AddToCartRequest): Call<AddToCartResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/cart/get/")
    fun getCart(@Header("Authorization") authToken: String): Call<ProductsInCart>

    @Headers("Content-Type: application/json")
    @POST("/api/auth/profile_update/")
    fun editProfileInfo(@Header("Authorization") authToken: String, @Body userData: EditPersonalInfoRequest): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/credit-cards/opts/get_all_credit_cards/")
    fun getCreditCards(@Header("Authorization") authToken: String): Call<Array<CreditCard>?>

    @Headers("Content-Type: application/json")
    @POST("/api/orders/make_purchase/")
    fun makePurchase(@Header("Authorization") authToken: String): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/credit-cards/opts/add/")
    fun addCreditCard(@Header("Authorization") authToken: String, @Body addCreditCardRequest: AddCreditCardRequest): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/favorites/add/")
    fun addToFavoriteList(@Header("Authorization") authToken: String, @Body productData: AddRemoveFavoriteListRequest): Call<AddRemoveFavoriteListResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/favorites/remove/")
    fun removeFromFavoriteList(@Header("Authorization") authToken: String, @Body productData: AddRemoveFavoriteListRequest): Call<AddRemoveFavoriteListResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/favorites/get/")
    fun getFavoriteList(@Header("Authorization") authToken: String): Call<ProductsInFavoriteList>

    @Headers("Content-Type: application/json")
    @POST("/api/products/opts/get_all_comments/")
    fun allComments(@Body commentRequest: CommentRequest): Call<Array<CommentDetails>?>

    @Headers("Content-Type: application/json")
    @GET("/api/notifications/my/")
    fun myNotification(@Header("Authorization") authToken: String): Call<Array<NotificationResponse>?>
  
    @Headers("Content-Type: application/json")
    @POST("/api/auth/password_reset_request/")
    fun forgotPassword(@Body userMail: ForgotPasswordRequest): Call<ForgotPasswordRequest>

    @Headers("Content-Type: application/json")
    @POST("/api/products/opts/add_comment/")
    fun addComment(@Header("Authorization") authToken: String, @Body commnentData: AddComment): Call<Success>

    @Headers("Content-Type: application/json")
    @GET("/api/orders/customer-orders/")
    fun getPreviousOrders(@Header("Authorization") authToken: String): Call<Array<Order>?>

    @Multipart
    @POST("/api/products/opts/add/")
    fun addProduct(@Header("Authorization") authToken: String,
                   @Part("name") name: RequestBody,
                   @Part("price") price: RequestBody,
                   @Part("stock") stock: RequestBody,
                   @Part("description") description: RequestBody,
                   @Part("subcategory_name") subcategoryName: RequestBody,
                   @Part("brand") brand: RequestBody,
                   @Part("discount") discount: RequestBody,
                   @Part image_file: MultipartBody.Part) : Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("/api/orders/vendor-orders/")
    fun getVendorOrders(@Header("Authorization") authToken: String): Call<Array<Purchase>?>

    @Headers("Content-Type: application/json")
    @POST("/api/orders/update-status/")
    fun updateOrderStatus(@Header("Authorization") authToken: String, @Body updateStatusRequest: UpdateStatusRequest): Call<Success>

    @POST("/api/auth/password_change/")
    fun changePassword(@Header("Authorization") authToken: String, @Body passwordChangeRequest: PasswordChangeRequest): Call<Success>

    @Headers("Content-Type: application/json")
    @POST("/api/products/search/")
    fun searchQuery(@Header("Authorization") authToken: String, @Body query: String): Call<Array<ProductDetails>?>
}
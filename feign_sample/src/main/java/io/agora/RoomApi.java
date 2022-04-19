package io.agora;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

// https://developer.netless.link/server-zh/home/server-room
interface RoomApi {
    @RequestLine("POST /rooms")
    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "Accept-Encoding: deflate, gzip;",
            "token: {sdkToken}",
            "region: {region}"
    })
    @Body("%7B\"limit\":{limit},\"isRecord\":true%7D")
    Room createRoom(@Param("sdkToken") String sdkToken,
                    @Param("region") String region,
                    @Param("limit") int limit
    );

    /**
     * Create Room Token
     *
     * @param uuid
     * @param sdkToken
     * @param region
     * @param lifespan Valid time (ms) 0 indicates permanent validity
     * @param role     Permission role, can be admin, writer, reader
     * @return
     */
    @RequestLine("POST /tokens/rooms/{uuid}")
    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "token: {sdkToken}",
            "region: {region}"
    })
    @Body("%7B\"lifespan\":{lifespan},\"role\":\"{role}\"%7D")
    String createRoomToken(
            @Param("uuid") String uuid,
            @Param("sdkToken") String sdkToken,
            @Param("region") String region,
            @Param("lifespan") int lifespan,
            @Param("role") String role
    );

    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "token: {sdkToken}",
            "region: {region}"
    })
    @RequestLine("GET /rooms/{uuid}")
    Room getRoomInfo(@Param("uuid") String uuid,
                     @Param("sdkToken") String sdkToken,
                     @Param("region") String region);


    @RequestLine("PATCH /rooms/{uuid}")
    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "token: {token}",
    })
    @Body("%7B\"isBan\":{isBan}%7D")
    Room banRoom(@Param("uuid") String uuid,
                 @Param("token") String token,
                 @Param("isBan") boolean isBan
    );
}
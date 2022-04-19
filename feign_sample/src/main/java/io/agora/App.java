package io.agora;

import com.google.gson.Gson;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.okhttp.OkHttpClient;

public class App {
    private static String sdkToken = "";

    public static void main(String[] args) {
        RoomApi roomApi = Feign.builder()
                .decoder(new GsonDecoder())
                .client(new OkHttpClient())
                .target(RoomApi.class, "https://api.netless.link/v5");
        Room room = roomApi.createRoom(sdkToken, "cn-hz", 100);
        System.out.println("room created : " + new Gson().toJson(room));

        Room roomFetch = roomApi.getRoomInfo(room.uuid, sdkToken, "cn-hz");
        System.out.println("room got : " + new Gson().toJson(roomFetch));

        String roomToken = roomApi.createRoomToken(room.uuid, sdkToken, "cn-hz", 0, "admin");
        System.out.println("roomToken got : " + roomToken);

        Room roomBan = roomApi.banRoom(room.uuid, sdkToken, true);
        System.out.println("room ban : " + new Gson().toJson(roomBan));

        Room roomBanRestore = roomApi.banRoom(room.uuid, roomToken, false);
        System.out.println("room banRestore : " + new Gson().toJson(roomBanRestore));
    }
}

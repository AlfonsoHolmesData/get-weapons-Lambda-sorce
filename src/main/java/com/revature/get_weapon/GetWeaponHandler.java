package com.revature.get_weapon;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class GetWeaponHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();
    private final WeaponRepository WeaponRepo = new WeaponRepository();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        LambdaLogger logger = context.getLogger();

        logger.log("RECEIVED EVENT: " + requestEvent);


//        Weapon weapon = WeaponRepo.getWeaponByName("GFR-9000").orElseThrow(NullPointerException::new);
//        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
//        responseEvent.setBody(mapper.toJson(weapon));
        List<Weapon> weapons = WeaponRepo.getAllWeapons();
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody(mapper.toJson(weapons));
        return responseEvent;

    }
}
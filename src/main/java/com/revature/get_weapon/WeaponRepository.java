package com.revature.get_weapon;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WeaponRepository {

    private final DynamoDBMapper dbReader;

    public WeaponRepository() {
        dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());
    }

    public List<Weapon> getAllWeapons() {
        return dbReader.scan(Weapon.class, new DynamoDBScanExpression());
    }

    public Optional<Weapon> getWeaponByName(String Name) {

        Map<String, AttributeValue> queryInputs = new HashMap<>();
        queryInputs.put("name", new AttributeValue().withS(Name));

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withFilterExpression("Name = name")
                .withExpressionAttributeValues(queryInputs);

        List<Weapon> queryResult = dbReader.scan(Weapon.class, query);

        return queryResult.stream().findFirst();

    }
}

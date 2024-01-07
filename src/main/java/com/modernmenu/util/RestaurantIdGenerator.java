package com.modernmenu.util;

import java.io.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.*;
import org.hibernate.id.*;

public class RestaurantIdGenerator implements IdentifierGenerator {

  static int value;
  static String prefix;

  static {
    prefix = "R";
  }

  public RestaurantIdGenerator() {

    value = DBConfigAndUtil.getTableCount("restaurant");

  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {
    try {
      value++;
      String id = RestaurantIdGenerator.prefix + String.format("%03d", value);
      return id;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}

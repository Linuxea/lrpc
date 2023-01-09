package com.linuxea.lrpc.server;

import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerStub {

  private final List<Class<?>> serverClass;

  public ServerStub(Socket socket) {
    this.serverClass = new ArrayList<>();
  }

  public void registerServer(Class<?>... classes) {
    Collections.addAll(this.serverClass, classes);
  }

  public Object dealIncome(Class<?> tclass, String declareMethodName,
      Class<?> declareReturnType,
      Class<?>[] declareParameterTypes, Object... args)
      throws Exception {
    for (Class<?> aClass : serverClass) {
      if (aClass.equals(tclass)) {
        for (Method method : aClass.getMethods()) {
          String name = method.getName();
          Class<?> returnType = method.getReturnType();
          Class<?>[] parameterTypes = method.getParameterTypes();
          if (name.equals(declareMethodName) && returnType == (declareReturnType)
              && parameterTypes == (declareParameterTypes)) {
            Object invoke = method.invoke(tclass.getDeclaredConstructor().newInstance(), args);
//            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                ObjectOutputStream oos = new ObjectOutputStream(bos);
//                OutputStream outputStream = socket.getOutputStream()) {
//              oos.writeObject(invoke);
//              outputStream.write(bos.toByteArray());
//            }
            return invoke;
          }
        }
      }
    }
    return null;
  }

}

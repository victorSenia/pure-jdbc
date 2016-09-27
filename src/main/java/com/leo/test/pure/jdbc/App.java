package com.leo.test.pure.jdbc;

import com.leo.test.pure.jdbc.controller.HomeController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
@ApplicationPath("/pure-jdbc")
public class App extends Application {
    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new HomeController());
        return set;
    }
}

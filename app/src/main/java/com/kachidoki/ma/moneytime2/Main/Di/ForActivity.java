package com.kachidoki.ma.moneytime2.Main.Di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mayiwei on 2017/2/16.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ForActivity {

}
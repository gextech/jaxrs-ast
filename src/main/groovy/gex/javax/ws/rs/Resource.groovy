package gex.javax.ws.rs

import gex.javax.ws.rs.ast.ResourceTransformation
import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.TYPE
import static java.lang.annotation.RetentionPolicy.SOURCE

/**
 * Created by domix on 12/15/14.
 */
@Documented
@Retention(SOURCE)
@Target(TYPE)
@GroovyASTTransformationClass(classes = ResourceTransformation)
@interface Resource {
  Class[] value() default []
}
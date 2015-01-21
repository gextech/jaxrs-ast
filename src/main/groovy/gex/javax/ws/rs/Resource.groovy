/*
 *
 * Copyright (C) 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
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
package gex.javax.ws.rs.ast

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import static org.codehaus.groovy.control.CompilePhase.SEMANTIC_ANALYSIS

/**
 * Created by domix on 12/15/14.
 */
@GroovyASTTransformation(phase = SEMANTIC_ANALYSIS)
class ResourceTransformation implements ASTTransformation {

  public void visit(ASTNode[] nodes, SourceUnit source) {
    if (!(nodes[0] instanceof AnnotationNode) || !(nodes[1] instanceof AnnotatedNode)) {
      throw new RuntimeException("Internal error: wrong types: ${node.class} / ${parent.class}");
    }

    AnnotatedNode parent = (AnnotatedNode) nodes[1];
    AnnotationNode node = (AnnotationNode) nodes[0];

    if (parent instanceof ClassNode) {
      ClassNode classNode = (ClassNode) parent;
      processClassNode(classNode)
    }
  }

  private processClassNode(ClassNode classNode) {
    classNode.getInterfaces().each { ClassNode interfaceNode ->
      interfaceNode.getAnnotations().each { AnnotationNode annotationNode ->

        def annotationInClassFound = classNode.getAnnotations().find { annotationNode }

        if (!annotationInClassFound) {
          classNode.addAnnotation(annotationNode)
        }
      }

      interfaceNode.getAllDeclaredMethods().each { MethodNode methodNode ->
        MethodNode method = classNode.getMethod(methodNode.name, methodNode.parameters)

        if (method) {
          methodNode.getAnnotations().each { AnnotationNode annotationNode ->
            def annotationFoundInMethod = methodNode.getAnnotations().find { annotationNode }
            if (!annotationFoundInMethod) {
              method.addAnnotation(annotationNode)
            }

            for (int i = 0; i < method.getParameters().size(); i++) {
              Parameter parameterInClass = method.getParameters()[i]
              Parameter parameterInInterface = methodNode.getParameters()[i]

              def annotationsFoundInParameterClass = parameterInClass.getAnnotations()
              def annotationsFoundInParameterInterface = parameterInInterface.getAnnotations()

              annotationsFoundInParameterInterface.each {
                if (!annotationsFoundInParameterClass.find { it }) {
                  parameterInClass.addAnnotation(it)
                }
              }
            }
          }
        }
      }
    }
  }
}

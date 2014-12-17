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
        classNode.addAnnotation(annotationNode)
      }

      interfaceNode.getAllDeclaredMethods().each { MethodNode methodNode ->
        MethodNode method = classNode.getMethod(methodNode.name, methodNode.parameters)

        if (method) {
          methodNode.getAnnotations().each { AnnotationNode annotationNode ->
            method.addAnnotation(annotationNode)
          }
        }
      }
    }
  }
}

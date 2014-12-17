package gex.javax.ws.rs

import gex.javax.ws.rs.sample.UserResource
import groovy.text.GStringTemplateEngine
import groovy.text.TemplateEngine
import spock.lang.Specification

/**
 * Created by domix on 12/15/14.
 */
class ResourceTransformationSpec extends Specification {
  GroovyClassLoader loader
  TemplateEngine templateEngine

  void setup() {
    templateEngine = new GStringTemplateEngine()
    loader = new GroovyClassLoader(getClass().getClassLoader())
  }

  String createSourceCodeForTemplate(final String template, final Map binding) {
    templateEngine.createTemplate(template).make(binding).toString()
  }

  def create_instance_of(final String sourceCode) {
    return create_instance_of(sourceCode, new Object[0])
  }

  def create_instance_of(final String sourceCode, def constructor_args) {

    def clazz = add_class_to_classpath(sourceCode)

    return clazz.newInstance(constructor_args as Object[])
  }

  def add_class_to_classpath(final String sourceCode) {
    loader.parseClass(sourceCode)
  }

  def dd = '''
package gex.javax.ws.rs

import javax.ws.rs.*
import javax.ws.rs.core.Response

@Resource
class DemoResource implements gex.javax.ws.rs.sample.UserResource {
  Response getUsers(@QueryParam("from") Long from, @QueryParam("size") Long size) {null}

  Response postUsers() {null}
}

'''

  def resource = '''
'''

  def "should foo"() {
    when:
      def c = create_instance_of(dd)
    then:
      c instanceof UserResource
  }
}

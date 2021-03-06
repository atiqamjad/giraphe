package com.creactiviti.giraphe.graphql;

import org.springframework.stereotype.Component;

import com.creactiviti.giraphe.graph.Element;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
public class IElement implements TypeBuilder {
  
  public static final String NAME = "IElement";
  public static final GraphQLTypeReference REF = Types.ref(NAME);

  @Override
  public GraphQLType build() {
    return Types.interfaceTypeBuilder()
                .name(NAME)
                .field(Fields.notNullStringField("id"))
                .typeResolver((env) -> {
                  Object source = env.getObject();
                  if(source instanceof Element) {
                    Element element = (Element) source;
                    return (GraphQLObjectType) env.getSchema().getType(element.type());
                  }
                  throw new IllegalArgumentException("Can't resolve type");
                })
                .build();
  }

}

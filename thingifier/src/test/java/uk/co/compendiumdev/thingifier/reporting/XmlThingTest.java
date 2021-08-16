package uk.co.compendiumdev.thingifier.reporting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.compendiumdev.thingifier.apiconfig.JsonOutputConfig;
import uk.co.compendiumdev.thingifier.core.domain.definitions.EntityDefinition;
import uk.co.compendiumdev.thingifier.core.domain.definitions.field.definition.Field;
import uk.co.compendiumdev.thingifier.core.domain.definitions.field.definition.FieldType;
import uk.co.compendiumdev.thingifier.core.domain.instances.EntityInstance;

public class XmlThingTest {

    EntityDefinition defn;
    EntityInstance instance;

    @BeforeEach
    public void createThingWithNestedObjectField(){

        defn = new EntityDefinition("thing", "things");

        defn.addField(Field.is("person", FieldType.OBJECT)
                .withField(
                        Field.is("firstname").
                                withExample("Bob")).
                        withField(
                                Field.is("surname").withExample("D'obbs")
                        ));


        instance = new EntityInstance(defn);
        instance.setValue("person.firstname", "Connie");
        instance.setValue("person.surname", "Dobbs");
    }

    @Test
    public void outputAsXml(){
        final JsonThing jsonThing = new JsonThing(new JsonOutputConfig());

        final String xmlOutput = new XmlThing(jsonThing).getSingleObjectXml(instance);
        System.out.println(xmlOutput);

        Assertions.assertTrue(xmlOutput.contains("<thing>"));
        Assertions.assertTrue(xmlOutput.contains("<person>"));
        Assertions.assertTrue(xmlOutput.contains("<firstname>Connie</firstname>"));
        Assertions.assertTrue(xmlOutput.contains("<surname>Dobbs</surname>"));
    }
}

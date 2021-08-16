package uk.co.compendiumdev.challenge.apimodel;

import uk.co.compendiumdev.thingifier.core.EntityRelModel;
import uk.co.compendiumdev.thingifier.core.domain.instances.EntityInstanceCollection;
import uk.co.compendiumdev.thingifier.core.domain.datapopulator.DataPopulator;

public class TodoAPIDataPopulator implements DataPopulator {

    @Override
    public void populate(final EntityRelModel erm) {

        String [] todos={
                        "scan paperwork",
                        "file paperwork",
                        "process payments",
                        "escalate late payments",
                        "pay invoices",
                        "process payroll",
                        "train staff",
                        "schedule meeting",
                        "tidy meeting room",
                        "install webcam"};

        EntityInstanceCollection todo = erm.getInstanceCollectionForEntityNamed("todo");

        for(String todoItem : todos){
            todo.createManagedInstance().
                    setValue("title", todoItem);
        }
    }
}

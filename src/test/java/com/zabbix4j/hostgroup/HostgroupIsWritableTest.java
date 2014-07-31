package com.zabbix4j.hostgroup;

import com.zabbix4j.ZabbixApiTestBase;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


/**
 * Created by Suguru Yajima on 2014/04/30.
 */
public class HostgroupIsWritableTest extends ZabbixApiTestBase {

    public HostgroupIsWritableTest() {
        super();
    }

    @Test
    public void testIsWritable1() throws Exception {
        DummyHostgroup dummyHostgroup = new DummyHostgroup(zabbixApi);
        Integer targetId = dummyHostgroup.create();

        try {
            HostgroupIsWritableRequest request = new HostgroupIsWritableRequest();
            request.addHostgroupId(targetId);

            HostgroupIsWritableResponse response = zabbixApi.hostgroup().isWritable(request);
            assertNotNull(response);

            logger.debug(getGson().toJson(response));

            assertThat(response.getResult(), Is.is(Boolean.TRUE));
        } finally {
            dummyHostgroup.delete(targetId);
        }
    }

    @Test
    public void testIsWritable2() throws Exception {
        Integer targetId = 99999;

        HostgroupIsWritableRequest request = new HostgroupIsWritableRequest();
        request.addHostgroupId(targetId);

        HostgroupIsWritableResponse response = zabbixApi.hostgroup().isWritable(request);
        assertNotNull(response);

        logger.debug(getGson().toJson(response));

        assertThat(response.getResult(), Is.is(Boolean.FALSE));

    }

}

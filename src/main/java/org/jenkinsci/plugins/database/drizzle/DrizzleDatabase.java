package org.jenkinsci.plugins.database.drizzle;

import hudson.Extension;
import hudson.util.Secret;
import org.drizzle.jdbc.DrizzleDriver;
import org.jenkinsci.plugins.database.AbstractRemoteDatabase;
import org.jenkinsci.plugins.database.AbstractRemoteDatabaseDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Set;

/**
 * @author Kohsuke Kawaguchi
 */
public class DrizzleDatabase extends AbstractRemoteDatabase {
    @DataBoundConstructor
    public DrizzleDatabase(String hostname, String database, String username, Secret password, String properties) {
        super(hostname, database, username, password, properties);
    }

    @Override
    protected Class<DrizzleDriver> getDriverClass() {
        return DrizzleDriver.class;
    }

    @Override
    protected String getJdbcUrl() {
        return "jdbc:drizzle://" + hostname + '/' + database;
    }

    @Extension
    public static class DescriptorImpl extends AbstractRemoteDatabaseDescriptor {
        @Override
        public String getDisplayName() {
            return "Drizzle/MySQL";
        }

        // TODO: couldn't really figure out how to validate the parameters
//        public FormValidation doCheckProperties(@QueryParameter String properties) throws IOException {
//            try {
//                if (validPropertyNames==null) {
//                    // this computation depends on the implementation details of MySQL JDBC connector
//                    GroovyShell gs = new GroovyShell(getClass().getClassLoader());
//                    validPropertyNames = (Set<String>)gs.evaluate(new InputStreamReader(DrizzleDatabase.class.getResourceAsStream("validate.groovy")));
//                }
//                Properties props = Util.loadProperties(properties);
//                for (Map.Entry e : props.entrySet()) {
//                    String key = e.getKey().toString();
//                    if (!validPropertyNames.contains(key))
//                        return FormValidation.error("Unrecognized property: "+key);
//                }
//                return FormValidation.ok();
//            } catch (Throwable e) {
//                return FormValidation.warning(e,"Failed to validate the connection properties");
//            }
//        }
    }
}

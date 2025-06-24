package pe.edu.cibertec.ProyectoFinalDiscos.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

//    @Value("${DB_BACK_URL}")
//    private String dbUrl;
//    @Value("${DB_BACK_USER}")
//    private String dbUser;
//    @Value("${DB_BACK_PASS}")
//    private String dbPass;
//    @Value("${DB_BACK_DRIVER}")
//    private String dbDriver;
//
//    @Bean
//    public HikariDataSource hikariDataSource() {
//
//        HikariConfig config = new HikariConfig();
//
//        /**
//         * Configurar propiedad de conexion a BD
//         */
//        config.setJdbcUrl(dbUrl);
//        config.setUsername(dbUser);
//        config.setPassword(dbPass);
//        config.setDriverClassName(dbDriver);
//
//        config.setMaximumPoolSize(20); //Establece el número máximo de conexiones que puede manejar el pool simultáneamente
//        config.setMinimumIdle(5); //Establece el número mínimo de conexiones inactivas
//        config.setIdleTimeout(300000); //Establece el tiempo máximo que una
//                                       // conexión puede permanecer inactiva antes de ser cerrada.
//        config.setConnectionTimeout(30000); //Establece el tiempo máximo de espera para obtener una conexión del pool
//
//        System.out.println("###### HikariCP initialized ######");
//        return new HikariDataSource(config);
//
//    }

}

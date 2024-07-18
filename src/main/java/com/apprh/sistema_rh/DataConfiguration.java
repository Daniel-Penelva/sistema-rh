package com.apprh.sistema_rh;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {

/* 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/sistema_rh?useTimezone=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();   
        adapter.setDatabase(Database.MYSQL);                                   // Configura o banco de dados a ser usado como MySQL.
        adapter.setShowSql(true);                                              // Hibernate exibi as instruções SQL geradas no console.
        adapter.setGenerateDdl(true);                                          // Hibernate gera automaticamente as instruções DDL
        adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");   // Essa plataforma de banco de dados (dialeto) a ser usada. Neste caso, está especificando MariaDBDialect, que é apropriado para MariaDB, um derivado do MySQL.
		adapter.setPrepareConnection(true);                                    // Configura se o Hibernate deve preparar a conexão
		return adapter;
    }
*/

}

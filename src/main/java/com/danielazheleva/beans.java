    @Bean
    public DataSource dataSource(){
        System.out.println(driverClass+" "+ url+" "+username+" "+password);
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(driverClass);
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        return source;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
        return namedParameterJdbcTemplate;
    }
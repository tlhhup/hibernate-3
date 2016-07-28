# hibernate-3
缓存和级联操作
###级联
hibernate对父子对象的级联操作分为：create，merge，save-update，delete，lock，refresh，evict，replicate，默认不设置级联。如果你希望一个操作被顺着关联关系级联传播，则必须在映射文件中指明，如：

	<one-to-one name="person" cascade="persist"/>

同时级联可以组合设置：

	<one-to-one name="person" cascade="persist,delete,lock"/>

可以通过使用cascade="all"来指定全部操作都顺着关联关系级联，默认值是cascade=none"，即任何操作都不会被级联。

注意有个特殊的级联风格：delete-orphan，只应用于**one-to-many**关联，表明delete()操作应该应用于所有从关联中删除的对象。

说明：

1. 通常在<mang-to-one>或<many-to-many>关系中应用级联没有什么意义，级联通常在<one-to-one>和<one-to-mang>关系中比较有用
2. 如果子对象的寿命限定在父对象的寿命只能，可通过指定cascade="all,delete-orphan"将其变为自动生命周期管理的对象。
3. 在<one-to-many>中如果没设置级联为cascade="delete-orphan",则父对象失去对某个自对象的应用时并不会导致自对象被自动删除。

父子关系的级联说明如下：

1. 如果父对象被persist，那么所有的对象也会被persist
2. 如果父对象被merge，那么所有自对象也会被merge
3. 如果父对象被save，update或saveorupdate，那么所有的自对象则会被saveorupdate
4. 如果父对象被删除，那么所有子对象也会被删除
5. 除非被标记为**cascade="delete-orphan"**,否则子对象失去父对象对其的引用时，什么都也不会发生。如果有特殊需求，应用程序可显示调用delete来删除子对象 

级联发生在调用期或写入期，所有的操作，如果允许，都在操作被执行的时候级联到可触及的关联实体上。但是，save-update和delete-orphan是在**session flush**的时候在作用到所有可触及的被关联对象上的。

###数据源设置
1. 使用c3p0数据源
	1. jar包：c3p0-0.9.2.1.jar、hibernate-c3p0-4.3.11.Final.jar、mchange-commons-java-0.2.3.4.jar
	2. hibernate配置文件中配置

			<!-- c3p0数据源 -->
	        <property name="hibernate.c3p0.max_size">10</property>
	        <property name="hibernate.c3p0.min_size">2</property>
	        <property name="hibernate.c3p0.timeout">5000</property>
	        <property name="hibernate.c3p0.max_statements">100</property>
	        <property name="hibernate.c3p0.idle_test_period">3000</property>
	        <property name="hibernate.c3p0.acquire_increment">2</property>
	        <!-- 指定连接池提供者 
	        	  如果没有指定，则通过配置文件进行自行推断
	        -->
	        <property name="hibernate.connection.provider_class"org.hibernate.c3p0.internal.C3P0ConnectionProvider</property>
###缓存
1. 一级缓存：属于session范围内的

		Session session = sessionFactory.openSession();
		
		//触发select语句
		session.get(Student.class, 5);
		
		//没有触发
		session.get(Student.class, 5);

		//清除缓存
		session.clear();
		
		//再次触发select语句
		session.get(Student.class, 5);
		
		session.close();
2. 二级缓存：属于sessionFactory范围，在3.2之前默认使用Ecache作为缓存实现
	1. jar包：ehcache-core-2.4.3.jar、hibernate-ehcache-4.3.11.Final.jar、slf4j-api-1.6.1.jar
	2. 配置文件：hibernate配置文件中

			<!-- 二级缓存 -->
	        <!-- 使能二级缓存 -->
			<property name="hibernate.cache.use_second_level_cache">true</property>
			<!-- 指定缓存提供商 -->
			<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
	3. 设置缓存的对象
		1. 在hibernate的配置文件中设置
			
				<!-- 指明缓存的类 -->
				<class-cache usage="read-only" class="com.hibernatecache.entity.Student"/>
		2. 在实体映射文件中设置
		
				<class name="Student" table="students">
       				 <cache usage="read-only"/>
		3. 属性说明：
			1. usage：缓存策略，transaction、read-write、read-only或nonstrict-read-write
			2. region：指定二级缓存的区域名
	3. 验证

			Session session = sessionFactory.openSession();
			
			//触发select语句
			session.get(Student.class, 5);
			
			session.close();
			
			session=sessionFactory.openSession();
			//没有触发查询语句
			session.get(Student.class, 5);
			
			sessionFactory.close();
	4. 查询缓存
		1. 配置文件

				<!-- 使能查询缓存 -->
				<property name="hibernate.cache.use_query_cache">true</property>
		2. 代码：
				
				Query query = session.createQuery("from Student");
				//设置缓存和缓存的名称
				query.setCacheable(true).setCacheRegion("dd").list();
				
				session.close();
				
				//调用该方法的前提是：开启了查询缓存
				sessionFactory.getCache().containsQuery("dd");
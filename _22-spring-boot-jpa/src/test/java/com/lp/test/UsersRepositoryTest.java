package com.lp.test;

import com.lp.App;
import com.lp.dao.*;
import com.lp.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 测试类
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class UsersRepositoryTest {
	@Resource
	private TestRepository testRepository;
	@Resource
	private UsersRepositoryByName usersRepositoryByName;
	@Resource
	private UsersRepositoryQueryAnnotation usersRepositoryQueryAnnotation;
	@Resource
	private UsersRepositoryCrudRepository usersRepositoryCrudRepository;
	@Resource
	private UsersRepositoryPagingAndSorting usersRepositoryPagingAndSorting;
	@Resource
	private UsersRepository usersRepository;
	@Resource
	private UsersRepositorySpecification usersRepositorySpecification;

	/**
	 * 测试环境搭建
	 */
	@Test
	public void testSave(){
		List<Users> list = new ArrayList<>();

		Users users = new Users();
		users.setAddress("重庆市江北区");
		users.setAge(23);
		users.setName("雷六");

		list.add(users);
		this.testRepository.saveAll(list);
	}

	/**
	 * Repository
	 */
	@Test
	public void findByName(){
		List<Users> list = this.usersRepositoryByName.findByName("王五");
		System.out.println(list.get(0));
	}

	@Test
	public void findByNameAndAge(){
		List<Users> list = this.usersRepositoryByName.findByNameAndAge("雷六", 23);
		System.out.println(list.get(0));
	}

	@Test
	public void findByNameLike(){
		List<Users> list = usersRepositoryByName.findByNameLike("张%");
		System.out.println(list.get(0));
	}

	@Test
	public void queryByNameUseHQL(){
		List<Users> list = usersRepositoryQueryAnnotation.queryByNameUseHQL("李四");
		System.out.println(list.get(0));
	}

	@Test
	public void queryByNameUseSQL(){
		List<Users> list = usersRepositoryQueryAnnotation.queryByNameUseHQL("张三");
		System.out.println(list.get(0));
	}

	@Test
	@Transactional //@Transactional与@Test 一起使用时 事务是自动回滚的。
	@Rollback(false) //取消自动回滚
	public void updateUsersNameById(){
		usersRepositoryQueryAnnotation.updateUsersNameById("雷神", 4);
	}

	/**
	 * CrudRepository
	 */
	@Test
	public void testCrudRepositorySave() {
		Users user = new Users();
		user.setAddress("天津");
		user.setAge(32);
		user.setName("张三丰");
		this.usersRepositoryCrudRepository.save(user);
	}

	@Test
	public void testCrudRepositoryUpdate() {
		Users user = new Users();
		user.setId(6);
		user.setAddress("南京");
		user.setAge(40);
		user.setName("张三丰");
		this.usersRepositoryCrudRepository.save(user);
	}

	@Test
	public void testCrudRepositoryFindOne() {
		Optional<Users> optional = this.usersRepositoryCrudRepository.findById(6);
		System.out.println(optional.get());
	}

	@Test
	public void testCrudRepositoryFindAll() {
		List<Users> list  =  (List<Users>)this.usersRepositoryCrudRepository.findAll();
		for (Users users : list) {
			System.out.println(users);
		}
	}

	@Test
	public void testCrudRepositoryDeleteById() {
		this.usersRepositoryCrudRepository.deleteById(6);
	}

	/**
	 * PagingAndSortingRepository
	 */
	@Test
	public void testPagingAndSortingRepositorySort() {
		//Order 定义排序规则
		//Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
		//Sort对象封装了排序规则
		//Sort sort = new Sort(order);
		/*Sort sort = new Sort(Sort.Direction.DESC, "id");
		List<Users> list = (List<Users>)this.usersRepositoryPagingAndSorting.findAll(sort);
		for (Users users : list) {
			System.out.println(users);
		}*/
	}

	@Test
	public void testPagingAndSortingRepositoryPaging() {
		//Pageable:封装了分页的参数，当前页，每页显示的条数。注意：他的当前页是从0开始。
		//PageRequest(page,size) page:当前页。size:每页显示的条数
		//Pageable pageable = new PageRequest(1, 2);
		Pageable pageable = PageRequest.of(1, 2);
		Page<Users> page = this.usersRepositoryPagingAndSorting.findAll(pageable);
		System.out.println("总条数："+page.getTotalElements());
		System.out.println("总页数"+page.getTotalPages());
		List<Users> list = page.getContent();
		for (Users users : list) {
			System.out.println(users);
		}
	}

	@Test
	public void testPagingAndSortingRepositorySortAndPaging() {
		//Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
		//Pageable pageable = new PageRequest(1, 2, sort);
		Pageable pageable = PageRequest.of(1, 2, Sort.Direction.DESC, "id");

		Page<Users> page = this.usersRepositoryPagingAndSorting.findAll(pageable);
		System.out.println("总条数："+page.getTotalElements());
		System.out.println("总页数"+page.getTotalPages());
		List<Users> list = page.getContent();
		for (Users users : list) {
			System.out.println(users);
		}
	}

	/**
	 * JapRepository
	 */
	@Test
	public void testJpaRepositorySort() {
		//Order 定义排序规则
		//Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
		//Sort对象封装了排序规则
		//Sort sort = new Sort(order);

		/*Sort sort = new Sort(Sort.Direction.ASC, "name","id");
		List<Users> list = this.usersRepository.findAll(sort);
		for (Users users : list) {
			System.out.println(users);
		}*/

	}

	/**
	 * JpaSpecificationExecutor
	 */
	@Test
	public void testJpaSpecificationExecutor1() {

		/**
		 * Specification<Users>:用于封装查询条件
		 */
		Specification<Users> spec = new Specification<Users>() {

			//Predicate:封装了 单个的查询条件
			/**
			 * Root<Users> root:查询对象的属性的封装。
			 * CriteriaQuery<?> query：封装了我们要执行的查询中的各个部分的信息，select  from order by
			 * CriteriaBuilder cb:查询条件的构造器。定义不同的查询条件
			 */
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// where name = '张三三'
				/**
				 * 参数一：查询的条件属性
				 * 参数二：条件的值
				 */
				Predicate pre = cb.equal(root.get("name"), "雷神");
				return pre;
			}
		};
		List<Users> list = this.usersRepositorySpecification.findAll(spec);
		for (Users users : list) {
			System.out.println(users);
		}
	}

	/**
	 * JpaSpecificationExecutor   多条件测试
	 */
	@Test
	public void testJpaSpecificationExecutor2() {

		/**
		 * Specification<Users>:用于封装查询条件
		 */
		Specification<Users> spec = new Specification<Users>() {

			//Predicate:封装了 单个的查询条件
			/**
			 * Root<Users> root:查询对象的属性的封装。
			 * CriteriaQuery<?> query：封装了我们要执行的查询中的各个部分的信息，select  from order by
			 * CriteriaBuilder cb:查询条件的构造器。定义不同的查询条件
			 */
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// where name = '张三三' and age = 20
				List<Predicate> list = new ArrayList<>();
				list.add(cb.equal(root.get("name"),"雷神"));
				list.add(cb.equal(root.get("age"),23));
				Predicate[] arr = new Predicate[list.size()];
				return cb.and(list.toArray(arr));
			}
		};
		List<Users> list = this.usersRepositorySpecification.findAll(spec);
		for (Users users : list) {
			System.out.println(users);
		}
	}

	/**
	 * JpaSpecificationExecutor   多条件测试第二种写法
	 */
	@Test
	public void testJpaSpecificationExecutor3() {

		/**
		 * Specification<Users>:用于封装查询条件
		 */
		Specification<Users> spec = new Specification<Users>() {

			//Predicate:封装了 单个的查询条件
			/**
			 * Root<Users> root:查询对象的属性的封装。
			 * CriteriaQuery<?> query：封装了我们要执行的查询中的各个部分的信息，select  from order by
			 * CriteriaBuilder cb:查询条件的构造器。定义不同的查询条件
			 */
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate equalName = cb.equal(root.get("name"), "雷神");
				Predicate equalAge = cb.equal(root.get("age"), 23);
				Predicate equalId = cb.equal(root.get("id"), 2);

				//(name = '张三' and age = 20) or id = 2
				return cb.or(cb.and(equalName, equalAge), equalId);
			}
		};

		Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "id");
		Page<Users> page = this.usersRepositorySpecification.findAll(spec, pageable);
		System.out.println("总条数："+page.getTotalElements());
		System.out.println("总页数："+page.getTotalPages());
		List<Users> list = page.getContent();
		for (Users users : list) {
			System.out.println(users);
		}
	}
}

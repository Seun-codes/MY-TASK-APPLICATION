package com.elizabeth.mytodoapp.Repository;

import com.elizabeth.mytodoapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {
    @Query(value = "select * from to_do_list where status =?1" , nativeQuery = true)
    List<TodoItem> listOfTodoByStatus(String status);
    @Modifying
    @Transactional
    @Query(value = "UPDATE to_do_list set status =?1 where todo_id =?2", nativeQuery = true)
    int updateTodoByIdAndStatus(String status, int id);
    @Query(value = "select * from to_do_list where  user_id =?1", nativeQuery = true)
    List<TodoItem> findAllUserById(int id);
    @Query(value = "select * from to_do_list where status =?1 and user_id =?2", nativeQuery = true)
    List<TodoItem> findUserByIdAndStatus(String status, int id);
}

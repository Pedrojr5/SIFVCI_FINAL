package com.index.stylefahion.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.index.stylefahion.models.Empleado;

public interface repoEmpleado extends JpaRepository<Empleado, Integer> {

    Empleado findEmpleadoByEmpid(Integer empid);

}

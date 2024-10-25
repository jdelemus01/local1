package com.velazquez.apirestpi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Actividad;

@Repository
public interface ActividadRepositorio extends JpaRepository<Actividad, Long>{
    public List<Actividad> findByMas18(boolean mas18);

    public List<Actividad> findByAireLibre(boolean aireLibre);
}

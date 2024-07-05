package pe.edu.cibertec.DSWII.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.DSWII.model.bd.Producto;
import pe.edu.cibertec.DSWII.model.dto.DtoEntity;

@Component
public class DtoUtil {

    public DtoEntity convertirADto (Object obj, DtoEntity mapper) {
        return new ModelMapper().map(obj, mapper.getClass());
    }

    public Object convertirAEntity(Object obj, Class<Producto> mapper) {
        return new ModelMapper().map(mapper, obj.getClass());
    }

}

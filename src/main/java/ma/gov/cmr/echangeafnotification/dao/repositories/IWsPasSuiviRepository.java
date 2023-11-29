package ma.gov.cmr.echangeafnotification.dao.repositories;

import ma.gov.cmr.echangeafnotification.dao.models.WsPasSuiviEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWsPasSuiviRepository extends JpaRepository<WsPasSuiviEntity, Long> {

}


package helpdesk.repository;

import helpdesk.pojo.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpdeskRepository extends JpaRepository<Ticket, Integer> {
}

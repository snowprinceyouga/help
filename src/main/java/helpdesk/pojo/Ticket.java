package helpdesk.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "support_id")
    private Integer supportId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    public Ticket(){
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSupportId() {
        return supportId;
    }

    public void setSupportId(Integer supportId) {
        this.supportId = supportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", clientId=" + clientId +
                ", supportId=" + supportId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

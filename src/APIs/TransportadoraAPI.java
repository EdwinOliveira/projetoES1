package APIs;

import BD.Repositorio_Ablazon;
import Encomenda.*;
import interfaces.TransportadoraInterface;

import static extras.funcoes_uteis.print;

public class TransportadoraAPI implements TransportadoraInterface {

    public void entregarEncomenda(Integer id) {
        Encomenda aux = Repositorio_Ablazon.getEncomendaId(id);

        if (aux != null) {
            if (aux.getEstado().getId() == 3) {
                Estado_Encomenda aux2 = Repositorio_Ablazon.getEstadoEncomendaID(4);
                aux.setEstado(aux2);

                print("Estado da encomenda com Id [" + aux.getId() + "] atualizado com sucesso para [" + aux2.getDesignacao() + "]!");
            } else
                print("Não é possível entregar a encomenda com id [" + aux.getId() + "]! Ela já se encontra entregue ou ainda não foi submetida para envio!");
        } else
            print("A encomenda que está a tentar entregar não existe!");
    }
}

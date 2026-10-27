package ec.edu.taller;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/** Completar la logica respetando el contrato descrito en README.md. */
public class GestorImpresiones {
    private PriorityQueue<TrabajoImpresion> colaTrabajos;
    private int capacidadMaxima;

    public GestorImpresiones(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.colaTrabajos = new PriorityQueue<>();
    }

    public boolean existeTrabajo(String codigo) {
        if (codigo == null) {
            return false;
        }
        for (TrabajoImpresion trabajo : colaTrabajos) {
            if (codigo.equals(trabajo.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarTrabajo(TrabajoImpresion trabajo) {
        if (trabajo == null ) {
            return false;
        }
        if(trabajo.getCodigo() == null){
            return false;
        }
        if(trabajo.getCodigo().trim().isEmpty()){
            return false;
        }
        if (colaTrabajos.size() >= capacidadMaxima) {
            return false;
        }
        if (existeTrabajo(trabajo.getCodigo())) {
            return false;
        }
        colaTrabajos.add(trabajo);
        return true;
    }


    public TrabajoImpresion consultarSiguienteTrabajo() {
        // TODO: consultar el trabajo de mayor prioridad sin retirarlo.
        if (colaTrabajos.isEmpty()) {
            return null;
        }else{
        return colaTrabajos.peek();
        }
    }

    public TrabajoImpresion procesarSiguienteTrabajo() {
        // TODO: retirar el de mayor prioridad y cambiar su estado a PROCESADO.
        TrabajoImpresion t;
        if (colaTrabajos.isEmpty()) {
            return null;
        }else{
            t=colaTrabajos.poll();
            t.setEstado("PROCESADO");
            return t;
        }
    }

    public int contarTrabajosPendientes() {
        // TODO: devolver la cantidad de trabajos en la cola.
        return colaTrabajos.size();
    }

    public int consultarEspaciosDisponibles() {
        // TODO: devolver capacidad maxima menos trabajos pendientes.
        return capacidadMaxima-colaTrabajos.size();
    }

    public List<TrabajoImpresion> listarTrabajos() {
        // TODO: devolver una lista ordenada por prioridad sin alterar la cola
        PriorityQueue<TrabajoImpresion> copiaCola = new PriorityQueue<>(this.colaTrabajos);

        List<TrabajoImpresion> listaOrdenada = new ArrayList<>();

        while (!copiaCola.isEmpty()) {
            listaOrdenada.add(copiaCola.poll());
        }

        return listaOrdenada;
    }
}

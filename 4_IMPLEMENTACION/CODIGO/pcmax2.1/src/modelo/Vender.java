
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Vender {

    private long idVenta;
    private Date fecha;
    private float importe;
    private String empleado;
    private List<DetalleVender> detallesVenta = new ArrayList<>();

    /**
     * @return the idVenta
     */
    public long getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the importe
     */
    public float getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }

    /**
     * @return the empleado
     */
    public String getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the detallesVenta
     */
    public List<DetalleVender> getDetallesVenta() {
        return detallesVenta;
    }

    /**
     * @param detallesVenta the detallesVenta to set
     */
    public void setDetallesVenta(List<DetalleVender> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }
    
    public void addDetalle(DetalleVender detalle)
    {
        boolean existe = false;
        for(DetalleVender item : this.detallesVenta)
        {
            if(item.getIdProducto() == detalle.getIdProducto())
            {
               //agregar
                item.setCantidad(item.getCantidad() + detalle.getCantidad());
                existe = true;
                break;
                
            }
        }
        if(!existe)
            this.detallesVenta.add(detalle);
    }
}

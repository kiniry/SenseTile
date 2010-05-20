/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.common.messages;

/**
 *
 * @author dragan
 */
public class MessageType {

    public enum PacketType
    {
        PACKET_AVAILABLE,
        PACKET_IS_NOT_AVAILABLE,
        WAITING_ON_PACKET,
        WRONG_PACKET_RECEIVED,
        PACKET_RECEIVED_CORRECTLY,
        PACKET_SENDED;
    }
    public enum PipeType
    {
        PIPE_BUS_INFO,
        PIPE_BUS_BUFFERING,
        PIPE_BUS_EOS,
        PIPE_BUS_ERROR,
        PIPE_START,
        PIPE_PAUSE,
        PIPE_STOP,
        PIPE_PLAY;
    }
    public enum Validity
    {
        VALID,
        INVALID
    }

    public enum TransmissionType
    {
        NO_TRAMSMISSION,
        BROADCASTING_PROCESS_FINISHED,
        BROADCASTING_PROCESS_STARTED,
        BROADCASTING_PROCESS_STOPPED,
        RECORDING_PROCESS_STOPPED,
        RECORDING_PROCESS_STARTED,
        RECORDING_PROCESS_FINISHED;
    }
}

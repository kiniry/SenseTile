/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sensetile.components;

import javax.swing.JButton;
import sensetile.common.components.SenseTileView;
import sensetile.common.sources.ISource;

/**
 *
 * @author SenseTile
 */
public class ButtonControler
{

    public ButtonControler()
    {
        super();
    }

    private void updateButtonStartSource(JButton btnStartSource, final ISource source)
    {
        assert btnStartSource != null : "btnStartSource cannot be a null.";
        
        btnStartSource.setEnabled(source != ISource.NO_SOURCE &&
                (!source.isPlaying() || source.isPaused()));

    }

    private void updateButtonPauseSource(JButton btnPauseSource, final ISource source)
    {
        assert btnPauseSource != null : "btnPauseSource cannot be a null.";
        btnPauseSource.setEnabled(source != ISource.NO_SOURCE &&
                source.isPlaying() && !source.isPaused());

    }

    private void updateButtonStopSource(JButton btnStopSource, final ISource source)
    {
        assert btnStopSource != null : "btnStopSource cannot be a null.";
         btnStopSource.setEnabled(source != ISource.NO_SOURCE &&
                (source.isPaused() || source.isPlaying()));
    }

    private void updateButtonRemoveSource(JButton btnRemoveSource, final ISource source)
    {
        assert btnRemoveSource != null : "btnRemoveSource cannot be a null.";
        btnRemoveSource.setEnabled(source != ISource.NO_SOURCE);
    }

    private void updateButtonBackwardSource(JButton btnBackwardSource, final ISource source)
    {
        assert btnBackwardSource != null : "btnBackwardSource cannot be a null.";
        btnBackwardSource.setEnabled(source != ISource.NO_SOURCE);
    }

    private void updateButtonFowardSource(JButton btnFowardSource, final ISource source)
    {
        assert btnFowardSource != null : "btnFowardSource cannot be a null.";
        btnFowardSource.setEnabled(source != ISource.NO_SOURCE);
    }

    public void doUpdateButtonsState( final SenseTileView senseTileView, final ISource source)
    {
        assert senseTileView != null : "View cannot be a null.";
        updateButtonStartSource(senseTileView.getBtnStartSource(), source);
        updateButtonPauseSource(senseTileView.getBtnPauseSource(), source);
        updateButtonStopSource(senseTileView.getBtnStopSource(), source);
        updateButtonRemoveSource(senseTileView.getBtnRemoveSource(), source);
        updateButtonBackwardSource(senseTileView.getBtnBackward(), source);
        updateButtonFowardSource(senseTileView.getBtnFoward(), source);
    }

    
}

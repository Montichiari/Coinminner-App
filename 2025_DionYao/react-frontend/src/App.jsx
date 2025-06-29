import React, { useState, useEffect } from 'react';

import axios from 'axios';

const App = () => {

    const [target, setTarget] = useState('');
    const [selectedDenoms, setSelectedDenoms] = useState([]);

    const [coins, setCoins] = useState([]);
    const [remainder, setRemainder] = useState(null);

    const [submittedTarget, setSubmittedTarget] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const allDenominations = [0.01, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 50, 100, 1000];

    // To instantly test connection with Dropwizard backend
    useEffect(() => {
        axios.get('http://localhost:8080/test')
            .then((response) => {
                console.log('GET /test response:', response.data);
            })
            .catch((err) => {
                console.error('GET /test failed:', err);
            });
    }, []);

    // On DenomButton click, check if was already selected. If yes, remove and deselect, if no, add and select.
    const toggleDenomination = (denom) => {
        setSelectedDenoms((prevState) =>
            prevState.includes(denom) ? prevState.filter((x) => x !== denom) : [...prevState, denom]
        );
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Client-side validations
        if (!target || selectedDenoms.length === 0) {
            setError('Please enter a target and select at least one denomination.');
            return;
        }

        if (isNaN(target) || Number(target) <= 0) {
            setError("Please enter a valid positive number.");
            return;
        }

        setLoading(true);
        setError('');
        setCoins([]);
        setRemainder(null);
        setSubmittedTarget(null);

        try {
            const response = await axios.post('http://localhost:8080/coins', {
                target: parseFloat(target),
                denoms: selectedDenoms
            });

            setSubmittedTarget(target);
            setCoins(response.data.coins || []);
            setRemainder(response.data.remainder ?? null);

        } catch (err) {
            if (err.response && err.response.data) {
                setError(err.response.data);
            } else {
                setError('Something went wrong when contacting the server.');
            }
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ display: 'flex', height: '100vh' }}>
            <div style={{ flex: 1, padding: '2rem', borderRight: '1px solid #ccc' }}>
                <h1>CoinMinner</h1>
                <p>Enter a target amount and select denominations to calculate the fewest coins.</p>

                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Target Amount:</label><br />
                        <input
                            type="number"
                            step="0.01"
                            value={target}
                            onChange={(e) => setTarget(e.target.value)}
                            required
                        />
                    </div>

                    <div style={{ marginTop: '1rem' }}>
                        <label>Select Denominations:</label>
                        <div style={{ display: 'flex', flexWrap: 'wrap', gap: '0.5rem', marginTop: '0.5rem' }}>
                            {allDenominations.map((denom) => (
                                <DenomButton
                                    key={denom}
                                    denom={denom}
                                    selected={selectedDenoms.includes(denom)}
                                    onClick={toggleDenomination}
                                >
                                </DenomButton>
                            ))}
                        </div>
                    </div>

                    <div style={{ marginTop: '1rem' }}>
                        <strong>Target:</strong> {target || '-'}<br />
                        <strong>Selected:</strong> {selectedDenoms.join(', ') || '-'}
                    </div>

                    <button type="submit" style={{ marginTop: '1rem' }}>
                        {loading ? 'Calculating...' : 'Submit'}
                    </button>

                    {error && <p style={{ color: 'red', marginTop: '1rem' }}>{error}</p>}
                </form>
            </div>

            <div style={{ flex: 1, padding: '2rem' }}>
                <h2>Result</h2>
                {submittedTarget && (
                    <>
                        <p><strong>Target:</strong> {submittedTarget}</p>
                        <p><strong>Fewest Coins:</strong> [{coins.join(', ')}]</p>
                        {remainder !== null && (
                            <p><strong>Remaining Amount:</strong> {remainder}</p>
                        )}
                    </>
                )}
            </div>
        </div>
    );
}
export default App


// Component for the denomination buttons
const DenomButton = ({ denom, selected, onClick }) => {
    return (
        <button
            type="button"
            onClick={() => onClick(denom)}
            style = {{
                padding: '0.5rem 1rem',
                backgroundColor: selected ? '#4acaa8' : '#f0f0f0',
                color: selected ? 'white' : 'black',
                border: '1px solid #ccc',
                borderRadius: '4px',
                cursor: 'pointer',
                width: '80px',
                height: '40px',
            }}
            >
            {`$${denom}`}
        </button>
    );
}
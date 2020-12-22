import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Checkbox from '@material-ui/core/Checkbox';
import Avatar from '@material-ui/core/Avatar';

import {render} from 'react-dom';
import SimpleGridList from "./SimpleGridList";
const useStyles = makeStyles((theme) => ({
    list: {
        overflowY: "scroll",
        overflow: 'hidden',
        height:200,
        backgroundColor: theme.palette.background.paper,
        '&::-webkit-scrollbar': {
            width: '0.4em'
        },
        '&::-webkit-scrollbar-track': {
            '-webkit-box-shadow': 'inset 0 0 6px rgba(0,0,0,0.3)',
            'border-radius': '10px',
        },
        '&::-webkit-scrollbar-thumb': {
            '-webkit-box-shadow': 'inset 0 0 6px rgba(0,0,0,0.5)',
            'border-radius': '10px',
        }
    },



}));

export const CheckboxListSecondary  = ({listof,filterkey,isbrand}) => {

    const classes = useStyles();
    const [checked, setChecked] = React.useState([]);


    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        setChecked(newChecked);
        console.log(newChecked)

        {isbrand ?  sessionStorage.setItem('brandlist', JSON.stringify(newChecked)) :
            sessionStorage.setItem('vendorlist', JSON.stringify(newChecked))}

        console.log(sessionStorage.getItem('brandlist'))


    };


        return (

            <List  dense className={classes.list}>
                {listof.filter(listof => listof.includes(filterkey)).map((value) => {
                    const labelId = `checkbox-list-secondary-label-${value}`;

                    return (
                        <ListItem key={value} button>

                            <ListItemText id={labelId} primary={`${value}`}/>
                            <ListItemSecondaryAction>
                                <Checkbox
                                    edge="end"
                                    onChange={handleToggle(value)}
                                    checked={checked.indexOf(value) !== -1}
                                    inputProps={{'aria-labelledby': labelId}}
                                />
                            </ListItemSecondaryAction>
                        </ListItem>

                    );

                })}
            </List>

        );


}
export default CheckboxListSecondary;
